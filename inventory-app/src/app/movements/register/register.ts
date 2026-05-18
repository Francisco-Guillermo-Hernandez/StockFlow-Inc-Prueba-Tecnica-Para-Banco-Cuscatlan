import { Component, OnInit, Signal, signal, computed } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
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
  form: FormGroup;
  products = signal<Product[]>([]);
  options: Option[] = [
    {
      name: 'Mover del inventario',
      value: 'out',
    },
    {
      name: 'Agregar al inventario',
      value: 'in',
    },
  ];
  validationErrors: Record<string, string> = {};
  submitError: string | null = null;
  submitSuccess: boolean = false;

  constructor(
    private readonly fb: FormBuilder,
    private readonly movementService: Movements,
    private readonly productService: Products,
    private readonly router: Router,
  ) {
    this.form = this.fb.group({
      productId: ['', Validators.required],
      quantity: ['', Validators.required],
      reason: ['', Validators.required],
      operation: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.loadProducts();
  }

  private loadProducts(): void {
    this.productService.listAll().subscribe({
      next: (products) => {
        this.products.set(products);
      },
      error: (error) => {
        console.error('Error loading products:', error);
        this.submitError = 'Error al cargar los productos';
      },
    });
  }

  public onSubmit(): void {
    this.validationErrors = {};
    this.submitError = null;
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
            this.submitError = error?.error?.message || 'Error al crear el movimiento';
          },
        });
    }
  }
}
