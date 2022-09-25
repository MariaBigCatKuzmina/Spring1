import {User} from "./user";
import {Product} from "./product";

export class Pageable {
  constructor(public pageNumber: number,
              public pageSize: number,
              public offset: number,
              public paged: boolean,
              public unpaged: boolean) {
  }
}

export class UserPage{
  constructor(public content:User[],
              public pageable:Pageable,
              public last:boolean,
              public totalPages:number,
              public totalElements:number,
              public numberOfElements:number,
              public number:number,
              public first:number,
              public size:number,
              public empty:boolean) {
  }

}

export class ProductPage{
  constructor(public content:Product[],
              public pageable:Pageable,
              public last:boolean,
              public totalPages:number,
              public totalElements:number,
              public numberOfElements:number,
              public number:number,
              public first:number,
              public size:number,
              public empty:boolean) {
  }
}
