# Tema 1 

PA - Tema 1 - Gigel si muntele de bani

Enuntul complet al problemelor se gaseste in folderul asociat temei. Mai jos sunt descrise rezolvarile problemelor. 

## Problema 1

Pentru prima problema, prima data sortez cele n calculatoare dupa metrica Pi, cu o complexitate de O(nlogn). 

Dupa aceea, intr-o singura iteratie prin tot vectorul de calculatoare sortat, efectuez urmatoarele: daca se ajunge la un
nou "nivel" (adica daca este nevoie sa upgradam toate calculatoarele din nivelul superior pentru a creste profitul), 
calculam cat trebuie platit pentru a face upgrade-urile. Daca ne incardam in bani, crestem profitul maxim si scadem 
numarul de bani care ne mai ramane. Altfel ne oprim din a mai face calcule, pentru ca nu ne mai raman destui bani.

Daca ne mai raman bani la finalul iteratiei, incercam sa cumparam upgrade-uri pentru toate calculatoarele, in limita
bugetului.

**Complexitatea temporala: O(nlogn + n) = O(nlogn)**.

**Complexitatea spatiala: O(n)**.

## Problema 2

Problema este literalmente tiparul rucsacului de la programare dinamica, unde mai avem o dimensiune in plus. 
*dp[i][j][k] reprezinta profitul cu primele i actiuni, cu bugetul maxim j si cu pierdere de cel mult k*. De mentionat
ca profitul intr-o iteratie este dat de stock[i].maxValuea - stock[i].current, iar pierderea de bani este data de
stock[i].current - stock[i].minValue.

**Complexitatea temporala: O(n^3)**.

**Complexitatea spatiala: O(n^3)**.

## Problema 3

Pentru aceasta problema, in primul rand, pe masura ce citim datele de intrare, cautam muntele minim global.

In functie de minimul gasit, Calculam de pe o parte si de pe cealalta numarul minim de taieri pe care trebuie 
sa il facem pentru a obtine o vale. Mai exista doua cazuri in care dimensiunile muntilor sunt in ordine crescatoare
sau descrecatoare, pentru care doar facem o vale din al doilea sau penultimul element.

**Complexitatea temporala: O(n + n) + O(n)**.

**Complexitatea spatiala: O(n)**.

## Problema 4
 
Problema este una de programare dinamica cu dimensiunea dp[n][3] pe care o codificam astfel: *dp[i][j] = combustibilul 
minim pe care trebuie sa il consumam pentru primii i munti si j taieturi pentru muntele i*. De mentionat ca numarul maxim 
pe care trebuie sa il facem pentru un munte este 2, mai mult de atat nu are sens. 

Cazul initial il avem pentru i = 1 (un munte), si j = 0..2, pentru care dp[i][j] = j * costul de taiere al muntelui i. 

Pentru cazul general, avem 5 situatii: Daca muntele curent i are inaltimea mai mica decat 0 daca facem j taieturi, 
inseamna ca acel caz nu va fi niciodata raspunsul, deci o notam cu LLONG_MAX. Pentru urmatoarele 3 cazuri, verificam
daca putem face taieturi astfel incat doua niveluri sa nu ajunga la aceeasi intaltime prin taieturi, si selectam 
numai din muntii i-1 numai valorile cu taieturi care respecta constrangerea. Pentru celalalt caz, alegem pur si simplu
minimul din dimensiunea anterioara si actualizam noua valoare.


**Complexitate temporala: O(3 * n) = O(n)**.

**Complexitate spatiala: O(3 * n) = O(n)**.

## Problema 5

Recurenta pentru aceasta problema este Sn = Sn-1 + Sn-3 + 3, cu, S1 = 1, S2 = 3, S3 = 6, S4 = 10. Folosind **exponentiere
pe matrici**, putem obtine rezultatul recurentei in *timp logaritmic*. Codul pentru inmultirea a doua matrici si ridicarea
la putere a unei matrice este preluat din laboratorul de programare dinamica 2 de pe ocw.cs.pub.ro

**Complexitate temporala: O(logn)**.

**Complexitate spatiala: O(1)**.