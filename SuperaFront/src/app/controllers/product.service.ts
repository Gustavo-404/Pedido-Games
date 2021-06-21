import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpCliente: HttpClient) { }

  findAllProducts(): Observable<any[]> {
    return this.httpCliente.get<any[]>(`${environment.baseUrl}/products`);
  }

  findProductById(id: number): Observable<any> {
    return this.httpCliente.get<any>(`${environment.baseUrl}/products/${id}`);
  }

  findProductByname(): Observable<any> {
    return this.httpCliente.get<any>(`${environment.baseUrl}/products/name`);
  }

  findProductByprice(): Observable<any> {
    return this.httpCliente.get<any>(`${environment.baseUrl}/products/price`);
  }

  findProductByscore(): Observable<any> {
    return this.httpCliente.get<any>(`${environment.baseUrl}/products/score`);
  }
  
}
