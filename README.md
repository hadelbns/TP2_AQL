*******************************************************Exo3********************************************************
Question 1:

Dans un test pour automatiser la méthode jouer de la classe Jeu, les objets qui dépendent de la classe Jeu et qui doivent être mockés sont :
--Joueur : Il est nécessaire de mocker l'objet Joueur pour contrôler son comportement lors du test. 
           En particulier, nous devons contrôler le montant de la mise et les méthodes debiter et crediter appelées sur l'objet 
           Joueur lors de l'exécution de la méthode jouer.
--De : Nous devons mocker les objets De pour simuler les lancers de dés. En utilisant des doubles (mocks) pour les dés, 
nous pouvons simuler différents résultats de lancer de dés pour tester différents scénarios de jeu.
--Banque : Bien que la banque soit utilisée dans la méthode jouer pour vérifier si elle est solvable, 
elle n'a pas besoin d'être mockée directement dans tous les cas. Cependant, si la méthode jouer doit effectuer
des transactions avec la banque (par exemple, pour créditer les gains du joueur), alors la banque devrait être mockée pour contrôler son comportement lors du test spécifique.

Question 2:

Nous allons écrire les scénarios suivants pour tester la méthode jouer :
--jeu est fermé : Tester si une exception est levée lorsque la méthode jouer est appelée alors que le jeu est fermé.
--le joueur est insolvable : Tester si une exception est levée lorsque le joueur n'a pas suffisamment d'argent pour jouer.
--le joueur gagne : Tester si le joueur est crédité du double de sa mise lorsque la somme des dés est égale à 7.
--le joueur perd : Tester si le joueur est débité de sa mise lorsque la somme des dés n'est pas égale à 7.

Question 4 :

Le test où le jeu est fermé est un test d'état.
Explication :
Un test d'état vérifie le comportement d'un système dans un état spécifique.
Dans ce cas, le test vérifie comment la classe `Jeu` réagit lorsque le jeu est dans un état fermé. 
Il ne se concentre pas sur les interactions avec d'autres objets, mais plutôt sur le comportement interne de la classe `Jeu` dans cet état particulier.
Pour ce test, nous nous attendons à ce que la méthode `jouer` de la classe `Jeu` lance une exception `JeuFermeException` lorsque le jeu est fermé. 
Le test vérifiera si cette exception est bien levée dans cette situation, ce qui confirmera que la classe `Jeu` se comporte correctement lorsque le jeu est fermé.

Question 5:

Pour tester le cas où le joueur est insolvable, vous pouvez mocker l'objet Joueur de manière à ce qu'il lève une exception lorsque la méthode debiter est appelée. 
En vérifiant que l'exception est levée, vous pouvez vous assurer que le jeu ne touche pas aux dés. 
Ce test est principalement un test d'état, Nous vérifions également qu'aucune interaction indésirable n'a lieu entre le jeu et les dés lorsque le joueur est insolvable. 
En vérifiant qu'aucune méthode lancer() n'est appelée sur l'objet De, nous nous assurons que le jeu ne tente pas de lancer les dés dans cette situation.
Cela garantit que le jeu se comporte conformément aux spécifications lorsqu'il est confronté à un joueur insolvable,
et que les interactions entre les différents objets sont correctement gérées




