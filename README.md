# Sprawdzarka
### Napisana na zaliczenie projektu zespołowego na Uniwersytecie Wrocławskim

Służy do automatycznego oceniania programów wysłanych przez użytkowników na 
podstawie testów dostarczonych wcześniej przez admina. 

Projekt składa się z dwóch aplikacji komunikujących się ze sobą za pomocą REST.

### Aplikacja WWW
Służy do autoryzacji użytkowników i udostępnia możliwość zdefiniowania zadań, testów oraz 
wysyłania swoich rozwiązań. Po wszystkim udostępnia użytkownikowi informację zwrotną 
dotyczącą zdobytych punktów. Aplikacja za pomocą REST komunikuje się z maszyną wirtualną w celu
skompilowania programu i otrzymania wyników.

#### Technologie
* Spring Boot
* Spring Security
* Hibernate
* Thymeleaf
* Bootstrap 

### Compilebox
Za pomocą [compilebox](https://github.com/remoteinterview/compilebox) korzystającego z dockera 
możemy skompilować nasz program na całkowicie odrębnej wirtualnej maszynie dzięki czemu nie musimy obawiać się
niebezpiecznych fragmentów kodu w dostarczonym przez użytkownika programie. Po wszystkim wysyłamy wyniki wyprodukowane przez program
z powrotem do aplikacji WWW.

### Widok
![ss1](/screens/ss1.png?raw=true "Screen1")