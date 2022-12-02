import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprovissionnementListComponent } from './approvissionnement-list.component';

describe('ApprovissionnementListComponent', () => {
  let component: ApprovissionnementListComponent;
  let fixture: ComponentFixture<ApprovissionnementListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApprovissionnementListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ApprovissionnementListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
