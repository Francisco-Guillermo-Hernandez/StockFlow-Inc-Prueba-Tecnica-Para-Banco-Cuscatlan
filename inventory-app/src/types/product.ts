export type Product = {
  id?: number,
  sku: string,
  name: string,
  description: string,
  currentStock: number,
  minStock: number,
  unitPrice: number
  weight: number,
  active: boolean,
  createdAt?: string,
  updatedAt?: string
}
