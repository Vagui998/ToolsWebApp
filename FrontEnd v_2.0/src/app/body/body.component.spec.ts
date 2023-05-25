import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { ToolsService } from '../services/tools.service';
import { Tools } from '../models/tool';
import { Brand } from '../models/brand';
import { User } from '../models/user';
import { of } from 'rxjs';

import { BodyComponent } from './body.component';

describe('BodyComponent', () => {
  let component: BodyComponent;
  let fixture: ComponentFixture<BodyComponent>;
  let mockToolService: jasmine.SpyObj<ToolsService>;

  beforeEach(async () => {
    // Create a mock of the ToolsService
    mockToolService = jasmine.createSpyObj('ToolsService', ['getPaginatedTools', 'deleteTool']);

    await TestBed.configureTestingModule({
      declarations: [BodyComponent],
      providers: [
        { provide: ToolsService, useValue: mockToolService },
        { provide: ActivatedRoute, useValue: { params: of({}) } }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(BodyComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should fetch tools on initialization', () => {
    expect(component.listOfTools).toEqual([
      {
        id: 1,
        imageURL: 'image1.jpg',
        brand: { id: 1, name: 'Brand 1' },
        description: 'Tool 1 description',
        name: 'Tool 1',
        price: 10.99
      },
      {
        id: 2,
        imageURL: 'image2.jpg',
        brand: { id: 1, name: 'Brand 1' },
        description: 'Tool 2 description',
        name: 'Tool 2',
        price: 19.99
      }
    ]);
    expect(component.totalItems).toBe(2);
  });

  it('should navigate to previous page', () => {
    component.page = 1;
    spyOn(component, 'fetchTools');

    component.previousPage();

    expect(component.page).toEqual(0);
    expect(component.fetchTools).toHaveBeenCalled();
  });

  it('should navigate to next page if not on the last page', () => {
    component.page = 0;
    component.totalItems = 5;
    spyOn(component, 'fetchTools');

    component.nextPage();

    expect(component.page).toEqual(1);
    expect(component.fetchTools).toHaveBeenCalled();
  });

  it('should not navigate to next page if on the last page', () => {
    component.page = 1;
    component.totalItems = 4;
    spyOn(component, 'fetchTools');

    component.nextPage();

    expect(component.page).toEqual(1);
    expect(component.fetchTools).not.toHaveBeenCalled();
  });

  it('should delete tool and reload page if the current page is not empty', () => {
    const mockBrand: Brand = { id: 1, name: 'Brand 1' };
    const mockTool: Tools = {
      id: 1,
      imageURL: 'image1.jpg',
      brand: mockBrand,
      description: 'Tool 1 description',
      name: 'Tool 1',
      price: 10.99
    };
    component.page = 0;
    component.listOfTools = [mockTool];
    spyOn(component, 'fetchTools');

    mockToolService.deleteTool.and.returnValue(of(void 0));

    component.delete(mockTool.id);

    expect(mockToolService.deleteTool).toHaveBeenCalledWith(mockTool.id);
    expect(component.listOfTools).toEqual([]);
    expect(component.fetchTools).toHaveBeenCalled();
  });


  it('should delete tool and reload page if the current page is not empty', () => {
    const mockBrand: Brand = { id: 1, name: 'Brand 1' };
    const mockTool: Tools = {
      id: 1,
      imageURL: 'image1.jpg',
      brand: mockBrand,
      description: 'Tool 1 description',
      name: 'Tool 1',
      price: 10.99
    };
    component.page = 0;
    component.listOfTools = [mockTool];
    spyOn(component, 'fetchTools');

    mockToolService.deleteTool.and.returnValue(of(void 0)); // Updated return value

    component.delete(mockTool.id);

    expect(mockToolService.deleteTool).toHaveBeenCalledWith(mockTool.id);
    expect(component.listOfTools).toEqual([]);
    expect(component.fetchTools).toHaveBeenCalled();
  });

});
