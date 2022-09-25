import {Component, OnInit} from '@angular/core';
import {Product} from "../model/product";
import {ProductServiceService} from "../product-service.service";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];

  constructor(private productService: ProductServiceService) {  }

  ngOnInit(): void {
    this.productService.findAll()
      .subscribe(response => {
        this.products = response.content
    }, error => {console.log(error);});
  }

  delete(id: number | null) {
    this.productService.delete(id).subscribe()
  }

}
