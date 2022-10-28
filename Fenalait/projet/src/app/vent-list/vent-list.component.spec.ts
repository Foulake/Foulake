import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VentListComponent } from './vent-list.component';

describe('VentListComponent', () => {
  let component: VentListComponent;
  let fixture: ComponentFixture<VentListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VentListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
