import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { z } from 'zod';
import { Product } from '~/types/product';
import { Products } from '~/services/products';
import { Router } from '@angular/router';

const productSchema = z.object({
  sku: z
    .string()
    .trim()
    .min(1, 'SKU es requerido')
    .max(100, 'SKU debe de contener menos de 100 caracteres')
    .regex(/^[a-zA-Z0-9\-_]+$/, 'SKU solo puede contener letras, números, guiones y guiones bajos'),
  name: z
    .string()
    .trim()
    .min(1, 'El Nombre es requerido')
    .max(20, 'El nombre debe de tener menos de 20 caracteres'),
  description: z
    .string()
    .min(4, 'Por favor ingrese una descripcion')
    .max(100, 'El Resumen debe de contener menos de 100 caracteres'),
  currentStock: z.preprocess(Number, z.number().int().min(2, 'El Stock debe de ser mayor o igual a 1')),
  minStock: z.preprocess(Number, z.number().int().min(1, 'El Stock debe de ser mayor o igual a 1')),
  unitPrice: z.preprocess(Number, z.number().min(1.01, 'El precio debe de ser mayor a 1.01')),
  weight: z.preprocess(Number, z.number().min(0.001, 'El peso debe de ser mayor a 1')),
  active: z.boolean().optional(),
});

type ProductForm = z.infer<typeof productSchema>;

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {
  form!: FormGroup;

  validationErrors: Record<string, string> = {};
  submitError: string | null = null;
  submitSuccess = false;

  constructor(
    private readonly fb: FormBuilder,
    private readonly productsService: Products,
    private readonly router: Router
  ) {
    this.form = this.fb.group({
      sku: [''],
      name: [''],
      description: [''],
      currentStock: ['0'],
      minStock: ['1'],
      unitPrice: ['1.01'],
      weight: ['0.001'],
      active: [true],
    });
  }

  public onSubmit(): void {
    this.validationErrors = {};
    this.submitError = null;
    this.submitSuccess = false;

    const formValue = this.form.value as Record<string, unknown>;
    const result = productSchema.safeParse(formValue);

    if (!result.success) {
      for (const issue of result.error.issues) {
        const key = issue.path[0] as string;
        if (!this.validationErrors[key]) {
          this.validationErrors[key] = issue.message;
        }
      }
      return;
    }

    const product = result.data as Product;
    product.active ??= true;

    this.productsService.createProduct(product).subscribe({
      next: () => {
        this.submitSuccess = true;
        this.form.reset({
          sku: '',
          name: '',
          description: '',
          currentStock: '0',
          minStock: '0',
          unitPrice: '0.01',
          weight: '0.001',
          active: true,
        });

        this.router.navigate(['/products/']);
      },
      error: (err) => {
        this.submitError = err?.message ?? 'Error al crear el producto';
      },
    });
  }
}
