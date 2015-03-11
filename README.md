# hashcode-2015
Hashcode 2015 des Titans

## Config

* [Debian 7.8](http://gemmei.acc.umu.se/debian-cd/7.8.0/amd64/iso-cd/debian-7.8.0-amd64-netinst.iso)
* [Sublime Text 2](https://gist.github.com/vshvedov/1370650)
* Java 8
* Github avec clé ssh

## Installs
* Bien faire les git configs!
```
git config --global user.name "John Doe"
git config --global user.email johndoe@example.com
```
* Activer authentification SSH sur ce dépôt
* Java [JDK](http://www.webupd8.org/2014/03/how-to-install-oracle-java-8-in-debian.html), Java JRE, version 8


## A garder ouvert pendant le challenge

Sur freenode
https://webchat.freenode.net/

hexamax-hashcode-2015

---------

Pour un pasting rapide de texte :

https://docs.google.com/document/d/1TN6HP0phgZ_WPAy6jcS6v7qGHqL89HtCA1SBSAE7HLw/edit

---------

Pour echanger des fichiers :

https://drive.google.com/folderview?id=0B5OaQePUhfU1fk9TZUswTUY1UkhzN0VqcmZ6ZVU3d29OWGJEVFFvLXBUalFSZWZjOGFuUFk&usp=sharing

## Workflow

Un seul fichier java *Main.java* par problème, dans un dossier par problème.
Les classes sont à faire en statique à l'intérieur
(Voir fichier [template](https://github.com/Groscheri/hashcode-2015/blob/master/template/Main.java))

*Tous* les attributs sont directement publics pour éviter les getters/setters

Dupliquez le fichier template à chaque problème

## Compiler du java
```
javac Main.java
java Main
```
