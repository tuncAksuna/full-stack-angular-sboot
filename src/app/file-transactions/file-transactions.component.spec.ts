import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FileTransactionsComponent } from './file-transactions.component';

describe('FileTransactionsComponent', () => {
  let component: FileTransactionsComponent;
  let fixture: ComponentFixture<FileTransactionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FileTransactionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FileTransactionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
