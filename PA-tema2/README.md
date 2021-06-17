# Tema 2

PA - Tema 2

Enuntul complet al problemelor se gaseste in folderul asociat temei. Mai jos sunt descrise rezolvarile problemelor.

## Problema 1

Pentru prima problema, trebuia doar sa codificam matricea ca fiind un graf, si sa efectuam o 
parcurgere in latime(BFS). In timpul algoritmului BFS, in functie de ce tip de pod avem in pozitia
curenta (vertical/orizontal/dublu, adaugam in coada nodurile adiacente. De asemenea bordam matricea 
data ca input cu noduri care nu au poduri pe ele, pentru a fi usor sa calculam distanta 
minima spre exterior.

Dupa ce am calculat distantele pentru fiecare nod folosind BFS, cautam in nodurile adaugate in procesul
de bordare pentru a gasi drumul minim spre exterior.

Complexitate temporala: numarul de noduri din graf este egal cu dimensiunea matricii, deci n * m, iar pentru
fiecare nod putem avea in cel mai rau caz drumuri spre alte 4 noduri, deci numarul de muchii este proportional cu 
4 * n * m, deci avem complexitatea **O(n \* m + 4 \* n \* m) = O(5 \* n \* m) = O(n * m)**.

**Complexitatea spatiala: O(3 \* n \* m) = O(n \* m)**.


## Problema 2

Problema se reduce de fapt la cautarea componentelor conexe intr-un graf neorientat. Pentru a putea efectua DFS
pentru a gasi componentele conexe, am codificat datele de intrare in felul urmator: am mapat toate mail-urile la
primul om care detine mail-ul respectiv. *Daca mai exista un om care detine acel mail, adaugam o muchie intre prima
persoana care detine acest mail si persoana curenta*. Astfel, se construieste un graf care conecteaza toate 
persoanele intre ele, si vom face DFS pe acest graf.

In urma DFS-ului, descoperim toate *componentele conexe* din graf. In functie de componentele gasite, construim
rezultatele folosind set-uri, pentru a elimina mail-urile duplicate, si sortam componentele in functie de lungimea
lor, asa cum se specifica in enunt. 

**Complexitate temporala: O(n \* k \* log k + n log n)**.

**Complexitate spatiala: O (n \* k)**.


## Problema 3 

Pentru a treia problema, prima data trebuie sa aplicam un *algoritm de backtracking* pentru a obtine toate combinarile
de n luate cate k. Dupa aceea, pentru fiecare combinare gasita, construim toate sumele de care avem nevoie in felul 
urmator: punem valorile initiale ale combinarii intr-un set. Parcurgem acest set de t-1 ori, si de fiecare data
adaugam in set elementele formate din valoare x curenta a parcurgerii, la care adunam toate valorile din combinarea
gasita. 

Dupa ce formam sumele, consturim un vector de frecventa pentru a calcula usor cea mai lunga subsecventa de sume
consecutive. In final, verificam daca aceasta subsecventa este cea mai lunga gasita de pana acum. 

Dupa ce aplicam acest algoritm pentru toate combinarile cerute, vom obtine rezultatul problemei.

**Complexitate temporala: O(combinari de n luate cate k \* k \* t)**.

Complexitate spatiala: Pentru fiecare combinare posibila, construim inca un set si un vector de lungime proportionala cu
k * t. Deci complexitatea ar fi tot cam **O(combinari de n luate cate k \* k \* t)**.