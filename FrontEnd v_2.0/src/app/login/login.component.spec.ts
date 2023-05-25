import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { LoginComponent } from './login.component';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { of } from 'rxjs';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let httpMock: HttpTestingController;
  let httpClient: HttpClient;
  let cookieService: CookieService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoginComponent],
      imports: [HttpClientTestingModule],
      providers: [CookieService]
    }).compileComponents();

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    httpClient = TestBed.inject(HttpClient);
    cookieService = TestBed.inject(CookieService);
    httpMock = TestBed.inject(HttpTestingController);
    fixture.detectChanges();
  });

  afterEach(() => {
    httpMock.verify();
    cookieService.delete('token');
    cookieService.delete('refreshToken');
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should log in successfully', fakeAsync(() => {
    const credentials = {
      username: 'testuser',
      password: 'testpassword'
    };

    const response = {
      access_token: 'test-access-token',
      refresh_token: 'test-refresh-token'
    };

    spyOn(httpClient, 'post').and.returnValue(of(response));
    spyOn(cookieService, 'set');

    component.username = credentials.username;
    component.password = credentials.password;
    component.login();
    tick();

    expect(httpClient.post).toHaveBeenCalledWith(
      'http://localhost:11234/api/v1/auth/authenticate',
      credentials,
      jasmine.any(Object)
    );
    expect(cookieService.set).toHaveBeenCalledWith('token', response.access_token);
    expect(cookieService.set).toHaveBeenCalledWith('refreshToken', response.refresh_token);
    expect(component.username).toEqual('testuser');
    expect(window.alert).toHaveBeenCalledWith('You are logged in as testuser');
  }));

  it('should handle login error', fakeAsync(() => {
    const credentials = {
      username: 'testuser',
      password: 'testpassword'
    };

    spyOn(httpClient, 'post').and.returnValue(
      of(undefined).pipe(() => {
        throw new Error('Test error');
      })
    );
    spyOn(window, 'alert');

    component.username = credentials.username;
    component.password = credentials.password;
    component.login();
    tick();

    expect(httpClient.post).toHaveBeenCalledWith(
      'http://localhost:11234/api/v1/auth/authenticate',
      credentials,
      jasmine.any(Object)
    );
    expect(window.alert).toHaveBeenCalledWith('Wrong login info');
  }));
});
