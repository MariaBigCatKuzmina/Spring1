import {Component, OnInit} from '@angular/core';
import {ProductServiceService} from "../product-service.service";
import {Product} from "../model/product";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss']
})
export class ProductFormComponent implements OnInit {

  product = new Product(null, "", 0);
  isError = false;
  errorMessage = "";

  constructor(private productService: ProductServiceService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.productService.findById(params['id'])
        .subscribe(result => {
            this.product = result;
          },
          err => {
            this.isError = true;
            this.errorMessage = err.error.message;
          })
    })
  }

  save() {
    this.productService.save(this.product)
      .subscribe(res => {
        console.log(res);
        this.router.navigateByUrl('/products')
      }, err => {
        this.isError = true;
        this.errorMessage = err.error.message;
      })
  }
}
