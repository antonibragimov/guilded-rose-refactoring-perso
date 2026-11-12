# Gilded Rose refactoring

Objectif : refactorer le code existant, puis ajouter les objets "Conjured" sans modifier la classe `Item`.

La spécification se trouve dans [GildedRoseRequirements_fr.md](GildedRoseRequirements_fr.md).

## Lancer les tests

```
./mvnw clean test
```

## Démarche

1. Filet de sécurité : tests de caractérisation sur le comportement existant.
2. Refactoring de `updateQuality`, un commit par changement : à chaque étape, les tests passent.
3. Ajout de Conjured en Test-Driven Development.
4. Template Method et Simple Factory : une classe de règle par type d'objet.

## Choix

- `Item` n'est pas modifié, comme l'exige la spécification.
- Sulfuras se base sur `isLegendary()` : quand elle renvoie `true`, `applyTo` bloque toute modification.
- Les règles n'ont pas d'état : la factory partage une seule instance par type.
- Conjured est reconnu par le début du nom, les autres objets par leur nom exact.
