# POKER

#### [zasady gry](https://pl.wikipedia.org/wiki/Poker_pi%C4%99ciokartowy_dobierany)

### Aby rozpocząć należy: 
1. uruchomić program Servera i wyzanczyć liczbę graczy [2-4].
2. następnie uruchamiamy odpowiednią ilość Klientów i dostosuwyjemy się do poleceń danych na konsoli.

### Protokół komunikacyjny:
- `"WAITING_FOR_CONNECTION"` - informuje klienta o oczekiwaniu na innych klientów
- `"WAITING"` - informuje klineta o oczekiwaniu na jego kolej
- `"YOUR_TURN"` - informuje klienta o jego turze
- `"DISPLAY_GAME"` - wyświetla dane o grze
- `"EXCHANGE_CARD"` - wymiana karty przez klienta
- `"BET"` - postawienie zakładu przez klineta
- `"TO_CALL"` - wykonanie "call-a" przez klienta
- `"YOU_WON"` - inforrmuje klienta o jego wygranej
- `"SOMEONE_WON"` - informuje klineta kto wygrał
- `"TIE"` - informuje klienta o remisie w grze

### Odpowiedzi klienta:
- `[exchange_card]` - numeryczny format wpisywania kart do wymiany to cyfry w jednej lini odzielone spacją np.`1 3 5`
- #### licytacja:
- `call` - wyrównanie stawki
- `[bet]` - postawienie zakładu, klient wpisuje sume, np.`20`
- `fold` - tzw. pass dla klienta


