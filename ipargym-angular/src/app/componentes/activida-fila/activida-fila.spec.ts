import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActividaFila } from './activida-fila';

describe('ActividaFila', () => {
  let component: ActividaFila;
  let fixture: ComponentFixture<ActividaFila>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ActividaFila],
    }).compileComponents();

    fixture = TestBed.createComponent(ActividaFila);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
