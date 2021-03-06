eCOM-RICM5-2015 - Création d'un site de eCommerce de vente de stickers personnalisés
====================================================================================
*Auteurs : Alan DAMOTTE, Rama Codazzi, Quentin Torck, Jérémy Hammerer, Guo Kai*
***

Présentation
------------
Le projet eCOM consiste à concevoir et développer une application web. Une motivation principale du projet eCOM est qu’il intègre dans sa mise en œuvre différentes thématiques étudiées dans les formations en informatique : en particulier, interfaces homme-machine, applications et architectures réparties, bases de données. Les aspects relatifs au génie logiciel sont également présents puisque le projet eCOM comprend la conception et la réalisation d’un produit logiciel qui satisfait certains critères de qualité (documentation, respect des normes, etc).

L’intégration de différentes thématiques présente les atouts suivants :

* Valorisation des enseignements acquis dans les différentes thématiques au profit d’un projet important et réaliste. Appréhension des (inter)dépendances entre les thématiques. Communications entre les différents réalisateurs du projet.

En outre, une deuxième motivation forte du projet eCOM est qu’il intègre des technologies et des standards récents et largement utilisés dans le domaine des applications Internet. Ce projet permet plus précisément de se placer en tant qu’utilisateurs de la technologie JEE (Java Enterprise Edition) destinée à la réalisation de serveurs d’information ou de serveurs de commerce électronique à base de composants distribués, transactionnels et persistants. Les réalisateurs sont confrontés par ce biais à la manipulation de mécanismes et de fonctions générales d’un système réparti : gestion de la désignation de composants distribués, configuration d’une application distribuée, association de propriétés non fonctionnelles aux composants (persistance, transactions), gestion des images persistantes des composants (liaisons avec une base de données). Des extensions au projet sont décrites ici et seront réalisées lors du projet RICOM/GICOM au semestre 2.

L’application réalisée devra offrir deux interfaces distinctes :

* Les administrateurs qui gèrent les stocks de produits, les comptes des utilisateurs de l’application et le suivi des orderes ; les consommateurs/acheteurs qui peuvent consulter des articles, les placer dans leur caddie et valider une ordere.

Chaque groupe devra élaborer un cahier des charges précis de l’application en termes de fonctionnalités et de critères d’ergonomie. Par ailleurs, le projet devra être réalisé en employant les méthodes et technologies présentées en cours. Ces différents aspects devront être validés par l’équipe pédagogique tout au long du projet.

Génération et consultation de la documentation
----------------------------------------------
1. Installer [Sphinx](http://sphinx-doc.org/latest/install.html#debian-ubuntu-install-sphinx-using-packaging-system)
2. Aller dans le dossier *doc* du répertoire
3. Effectuer la commande suivante : 

	```
	make html
	```
4. La documentation est accessible via la page index.html dans le code généré *build/html*
5. Les sources sont modifiables au besoin
 
