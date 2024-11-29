import { Injectable, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';


@NgModule({
  declarations: [],
  imports: [CommonModule],
})

export class Boite {
  constructor(
    public id:Number | null,
    public quantite: Number,
    public name: String,
    public description: String,
    public pointGeo: String
  ) {}
}
