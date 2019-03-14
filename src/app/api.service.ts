import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {HttpHeaders} from '@angular/common/http';
import {Book} from './book';
import {URLSearchParams} from '@angular/http';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'my-auth-token'
  })
};

@Injectable({
  providedIn: 'root'
})
export class APIService {
  borrower(borrower) {


    return this.httpClient.post(`${this.API_URL}borrow`, borrower, {responseType: 'text'});
  }

  reserve(borrower) {


    return this.httpClient.post(`${this.API_URL}reserve`, borrower, {responseType: 'text'});
  }

  return(returnItem) {


    return this.httpClient.post(`${this.API_URL}return`, returnItem, {responseType: 'text'});


  }

  constructor(private httpClient: HttpClient) {
  }

  API_URL = 'http://localhost:9000/api/';

  getLibrary() {
    return this.httpClient.get(`${this.API_URL}library`);

  }

  getReader() {
    return this.httpClient.get(`${this.API_URL}reader`);

  }

  getDVD() {
    return this.httpClient.get(`${this.API_URL}items`);

  }

  getAvailable() {
    return this.httpClient.get(`${this.API_URL}contacts`)
  }

  getSpace() {
    return this.httpClient.get(`${this.API_URL}space`)
  }

  deleteItem(id) {
    return this.httpClient.post<any>(`${this.API_URL}delete`, id);
  }

  searchTitle(data) {
    return this.httpClient.post<any>(`${this.API_URL}search`, data);
  }

  register(userData) {
    return this.httpClient.post<any>(this.API_URL, userData);
  }

  getBorrowerDetails() {
    return this.httpClient.get(`${this.API_URL}report`)
  }


  sendID(id) {


    return this.httpClient.post(`${this.API_URL}work`, id, {responseType: 'text'});

  }

  sendContacts(Contact) {


    return this.httpClient.post(`${this.API_URL}play`, Contact);

  }

  sendReader(reader) {


    return this.httpClient.post(`${this.API_URL}reader`, reader, {responseType: 'text'});

  }

  sendBook(book) {


    return this.httpClient.post(`${this.API_URL}book`, book, {responseType: 'text'});

  }

  sendDVD(dvd) {


    return this.httpClient.post(`${this.API_URL}dvd`, dvd, {responseType: 'text'});

  }


  sendText(txt: string) {
    return this.httpClient.post(`${this.API_URL}id`, txt, {responseType: 'text'});

  }

}
