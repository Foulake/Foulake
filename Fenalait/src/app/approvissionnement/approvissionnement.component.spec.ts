import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprovissionnementComponent } from './approvissionnement.component';

describe('ApprovissionnementComponent', () => {
  let component: ApprovissionnementComponent;
  let fixture: ComponentFixture<ApprovissionnementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApprovissionnementComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ApprovissionnementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
