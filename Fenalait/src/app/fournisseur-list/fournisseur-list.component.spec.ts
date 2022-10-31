import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FournisseurListComponent } from './fournisseur-list.component';

describe('FournisseurListComponent', () => {
  let component: FournisseurListComponent;
  let fixture: ComponentFixture<FournisseurListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FournisseurListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FournisseurListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
