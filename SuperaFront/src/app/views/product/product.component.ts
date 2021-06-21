import { ProductService } from './../../controllers/product.service';
import { Component, OnInit } from '@angular/core';
import { Products } from 'src/app/models/Product';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  cartList: any[] = []
  productsList: any[] = []
  frete: number = 0
  subtotal: number = 0
  total: number = 0

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.getAllProduct()
  }

  getAllProduct(): void {
    this.productService.findAllProducts().subscribe(result => this.productsList = result)
  }

  filterBy(filter: any): void {
    if (filter.value == "name") {
      this.productService.findProductByname().subscribe(result => this.productsList = result)
    }
    if (filter.value == "price") {
      this.productService.findProductByprice().subscribe(result => this.productsList = result)

    }
    if (filter.value == "score") {
      this.productService.findProductByscore().subscribe(result => this.productsList = result)

    }
  }

  addToCart(product: Products): void {
    this.cartList.push(product) // adiciona o elemento na lista do carrinho
    this.frete += 10
    this.subtotal += product.price
    alert("Produto adicionado ao carrinho!")
  }

  removeToCart(product: Products): void {
    this.cartList.splice(this.cartList.indexOf(product), 1) //remove o elemento na lista do carrinho
    this.frete -= 10
    this.subtotal -= product.price
    alert("Produto removido do carrinho!")
  }

  calculoTotal(): void {
    if (this.cartList.length === 0) {
      alert("O carrinho esta vazio!")
      location.reload()
    }
    else {
      if (this.subtotal >= 250) {
        this.frete = 0
      }
      this.total = this.frete + this.subtotal;
    }
  }

  fim(): void {
    alert("Obrigado por comprar conosco, compra realizada com sucesso!")
    this.cartList = []
    this.frete = 0
    this.subtotal = 0
    this.total = 0
  }

}
