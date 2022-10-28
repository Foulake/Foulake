import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClientListComponent } from './client-list/client-list.component';
import { VentListComponent } from './vent-list/vent-list.component';
import { FournisseurListComponent } from './fournisseur-list/fournisseur-list.component';
import { ProduitListComponent } from './produit-list/produit-list.component';
import { LocaliteListComponent } from './localite-list/localite-list.component';
import { MagasinComponent } from './magasin/magasin.component';
import { PaiementComponent } from './paiement/paiement.component';
import { UserComponent } from './user/user.component';
import { FormsModule } from '@angular/forms';
import { CreateClientComponent } from './create-client/create-client.component';
import { MenuComponent } from './menu/menu.component'; 

@NgModule({
  declarations: [
    AppComponent,
    ClientListComponent,
    VentListComponent,
    FournisseurListComponent,
    ProduitListComponent,
    LocaliteListComponent,
    MagasinComponent,
    PaiementComponent,
    UserComponent,
    CreateClientComponent,
    MenuComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
 