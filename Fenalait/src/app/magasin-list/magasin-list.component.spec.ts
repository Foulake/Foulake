import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MagasinListComponent } from './magasin-list.component';

describe('MagasinListComponent', () => {
  let component: MagasinListComponent;
  let fixture: ComponentFixture<MagasinListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MagasinListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MagasinListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
