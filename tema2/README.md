# Tema 2 - Gigel si mafiotii

In esenta trebuie sa reducem niste probleme de teoria grafurilor care sunt mascate ca niste familii mafiote care au legaturi intre ele. Fiecare dintre probleme este redusa intr-un fel sau altul la problema [SAT](https://en.wikipedia.org/wiki/Boolean_satisfiability_problem). Dupa ce am construit reducerea, un pachet de python **python-sat** se ocupa de rezolvarea problemelor. De asemenea trebuie interpretate solutiile primite ca output de la sat-solver.

## Task 1

In esenta trebuie redusa problema *k-colorarii* la *SAT*. Dupa codificare, trebuie interpretat raspunsul dat de catre sat-solver in functie de codificarea variabilelor noastre de intrare.

## Task 2

Trebuie redusa problema *k-clicii* la *SAT*. Dupa codificare, trebuie interpretat raspunsul dat de catre sat-solver in functie de codificarea variabilelor noastre de intrare.

## Task 3 

Aici trebuie sa ne folosim de reducerea de la *task 2* pentru a reduce problema *minimum vertex cover* la *k-clica*, 
si de la *k-clica* la *SAT* prin tranzitivitate. Dupa codificare, trebuie interpretat raspunsul dat de catre sat-solver in functie de codificarea variabilelor noastre de intrare.

## Bonus

La bonus vom folosi un alt format pentru problema **SAT** care permite unele clauze optionale, pentru a reduce in mod direct *minimum vertex cover* la *SAT*. Dupa codificare, trebuie interpretat raspunsul dat de catre sat-solver in functie de codificarea variabilelor noastre de intrare.

Detaliile despre tema se gasesc in [enuntul problemei](https://github.com/btudorache/AA/blob/master/tema2/AA__Tema_2.pdf).

Detaliile de implementare se gasesc in *old_README*.

