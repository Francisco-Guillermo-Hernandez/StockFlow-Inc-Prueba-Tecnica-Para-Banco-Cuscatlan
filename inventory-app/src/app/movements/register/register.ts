import { Component, OnInit, Signal, signal, computed } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { Movements } from '~/services/movements';
import { Products } from '~/services/products';
import { Product } from '~/types/product';
import { z } from 'zod';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';


const movementSchema = z.object({
  productId: z.string().min(1, 'Por favor seleccione un producto'),
  quantity: z
    .number()
    .min(1, 'La cantidad es requerida')
    .int('La cantidad debe ser un número entero')
    .positive('La cantidad debe ser mayor a 0'),

  reason: z
    .string()
    .min(10, 'Por favor ingrese una razón con mínimo 10 caracteres')
    .max(100, 'La razón no debe exceder 100 caracteres')
    .refine(
      (val) => val.trim().length >= 10,
      'Por favor ingrese una razón por la cual se hace el movimiento',
    ),
});

type MovementFormData = z.infer<typeof movementSchema>;

type Option = {
  name: string;
  value: string;
};

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register implements OnInit {
  public form: FormGroup;
  public products = signal<Product[]>([]);
  public options: Option[] = [
    {
      name: 'Mover del inventario',
      value: 'out',
    },
    {
      name: 'Agregar al inventario',
      value: 'in',
    },
  ];
  public validationErrors: Record<string, string> = {};
  public submitError  = signal<string | null>(null);
  public submitSuccess: boolean = false;
  public mode = signal('create');
  public productId = signal<string | null>('');

  constructor(
    private readonly fb: FormBuilder,
    private readonly movementService: Movements,
    private readonly productService: Products,
    private readonly router: Router,
    private readonly route: ActivatedRoute,
  ) {
    this.form = this.fb.group({
      productId: ['', Validators.required],
      quantity: ['', Validators.required],
      reason: ['', Validators.required],
      operation: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    const fromMode = this.route.snapshot.data['mode'];
    this.productId.set(id);
    this.mode.set(fromMode);

    this.loadProducts();

    console.info(`from mode ${fromMode}`)

    // if (mode() == 'all') {
    // }

    if (fromMode === 'edit' && id !== null) {
      this.getProductDetails(id);

      console.log('byProduct')
    }

  }

  private loadProducts(): void {
    this.productService.listAll().subscribe({
      next: (products) => {
        this.products.set(products);
      },
      error: (error) => {
        console.error('Error loading products:', error);
        this.submitError.set('Error al cargar los productos');
      },
    });
  }

  public onSubmit(): void {
    this.validationErrors = {};
    this.submitSuccess = false;

    const formData = {
      productId: this.form.get('productId')?.value,
      quantity: this.form.get('quantity')?.value,
      reason: this.form.get('reason')?.value,
      operation: this.form.get('operation')?.value,
    };

    const formValue = this.form.value as Record<string, unknown>;
    const result = movementSchema.safeParse(formValue);

    if (!result.success) {
      for (const issue of result.error.issues) {
        const key = issue.path[0] as string;
        if (!this.validationErrors[key]) {
          this.validationErrors[key] = issue.message;
        }
      }
      return;
    }

    console.info(formData)

    const validatedData = movementSchema.parse(formData);

    const operation = formData.operation;

    const quantity = operation === 'in' ? validatedData.quantity : validatedData.quantity * -1;

    console.log(`${operation}`)

    // const productData = computed<Product>(() => {
    //   return this.products().find((e) => e.id === formData.productId);
    // })

    const productData = this.products().find((e) => e.id === formData.productId);

    if (productData && operation === 'out' && quantity >= productData.currentStock ) {
      alert('No se puede retirar mas productos de la cantidad que hay en stock');
    } else {

      this.movementService
        .createProduct({
          productId: formData.productId,
          quantity,
          reason: validatedData.reason,
          operationName: operation,
        })
        .subscribe({
          next: () => {
            this.submitSuccess = true;

            this.form.reset();
            this.router.navigate(['/movements/']);
          },
          error: (error) => {
            this.submitSuccess = false;
            console.error('Error creating movement:', error);
            this.submitError.set(this.getErrorMessage(error));
          },
        });
    }
  }

  private getProductDetails(id: string): void {
    this.productService.productById(id).subscribe({
      next: data => {
        this.form.patchValue({ productId:  data.id})
      },
      error: error => {
        console.error(error);
      }
    })
  }

  private getErrorMessage(error: unknown): string {
    if (error instanceof HttpErrorResponse) {
      const body = error.error;
      if (body && typeof body === 'object' && 'message' in body && typeof (body as any).message === 'string') {
        return (body as any).message;
      }
      if (typeof body === 'string') {
        try {
          const parsed = JSON.parse(body);
          if (parsed && typeof parsed.message === 'string') {
            return parsed.message;
          }
        } catch {
          return body;
        }
      }
      if (error.message) {
        return error.message;
      }
    }

    if (typeof error === 'object' && error !== null && 'message' in error && typeof (error as any).message === 'string') {
      return (error as any).message;
    }

    return 'Error al crear el movimiento';
  }
}
