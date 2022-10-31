import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocaliteListComponent } from './localite-list.component';

describe('LocaliteListComponent', () => {
  let component: LocaliteListComponent;
  let fixture: ComponentFixture<LocaliteListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LocaliteListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LocaliteListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
