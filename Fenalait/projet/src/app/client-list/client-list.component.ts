import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Client } from '../client';
import { ClientService } from '../client.service';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.scss']
})
export class ClientListComponent implements OnInit {
  clients!: Client[];

  constructor(private router:Router, private clientService:ClientService) { }

  ngOnInit(): void {
    this.getClients();
  }
  private getClients(){
   this.clientService.getClientList().subscribe(data=>{
    this.clients=data

   });

  }
  updateClient(id:number){
    this.router.navigate(['modifier client',id]);
  
  }
  deleteClient(id:number){
    if(confirm("Êtes-vous sur de supprimer")){
      
    }
  }

}
