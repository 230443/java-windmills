import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TurbineControlComponent } from './turbine-control.component';

describe('TurbineControlComponent', () => {
  let component: TurbineControlComponent;
  let fixture: ComponentFixture<TurbineControlComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TurbineControlComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TurbineControlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
