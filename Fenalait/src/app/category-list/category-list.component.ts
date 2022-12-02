import { Component, OnInit } from '@angular/core';
import { Category } from '../category';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.scss']
})
export class CategoryListComponent implements OnInit {
 category!:Category
  constructor() { }

  ngOnInit(): void {
  }

}
