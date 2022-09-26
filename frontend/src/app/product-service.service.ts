import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ProductPage} from "./model/page";
import {Product} from "./model/product";

@Injectable({
  providedIn: 'root'
})
export class ProductServiceService {

  constructor(private http: HttpClient) { }

  public findAll(){
    return this.http.get<ProductPage>("api/v1/products");
  }

  public findById(id: number){
    return this.http.get<Product>(`api/v1/products/${id}/get`);
  }

  public save(product: Product) {
    return this.http.post<Product>('api/v1/products', product);
  }

  public delete(id: number | null) {
    return this.http.delete<Product>('api/v1/products/${id}')
  }
}
