import { Component, OnInit } from '@angular/core';
import { Client } from '../client';

@Component({
  selector: 'app-create-client',
  templateUrl: './create-client.component.html',
  styleUrls: ['./create-client.component.scss']
})
export class CreateClientComponent implements OnInit {
  client: Client = new Client(); 
  constructor() { }

  ngOnInit(): void {
  }
  onSubmit(){
     
  }

}
