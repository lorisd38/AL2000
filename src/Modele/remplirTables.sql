INSERT INTO LesPersonnesA values ('Dylan', 'Bob');
INSERT INTO LesPersonnesA values ('Michael', 'Georges');
INSERT INTO LesPersonnesA values ('Spielberg', 'Steven');
INSERT INTO LesPersonnesA values ('Dylon', 'Bon');
INSERT INTO LesPersonnesA values ('Cameron','James');
INSERT INTO LesPersonnesA values ('Worthington','Sam');
INSERT INTO LesPersonnesA values ('Saldana','Zoé');
INSERT INTO LesPersonnesA values ('Branagh', 'Kenneth');
INSERT INTO LesPersonnesA values ('James', 'Lily');
INSERT INTO LesPersonnesA values ('Bonham Carter', 'Helena');
INSERT INTO LesPersonnesA values ('Orlowski', 'Jeff');
INSERT INTO LesPersonnesA values ('Nolan', 'Christopher');
INSERT INTO LesPersonnesA values ('DiCaprio', 'Leonardo');
INSERT INTO LesPersonnesA values ('Gordon-Levitt', 'Joseph');

INSERT INTO LeCatalogue values
('Avatar', '20th Century Fox', (select REF(p) from LesPersonnesA p where p.nom = 'Cameron'),
 DATE '2009-01-01', tacteurs((select REF(p) from LesPersonnesA p where p.nom = 'Worthington'), (select REF(p) from LesPersonnesA p where p.nom = 'Saldana')),
 10, 'L action se déroule en 21543 sur Pandora, une des lunes de Polyphème, une planète géante gazeuse en orbite autour d Alpha Centauri A, le système stellaire le plus proche de la Terre. L exolune, recouverte d’une jungle luxuriante, est le théâtre du choc entre des humains, venus exploiter un minerai rare susceptible de résoudre la crise énergétique sur Terre, et la population autochtone, les Navis, qui vivent en parfaite symbiose avec leur environnement et tentent de se défendre face à l invasion militarisée. Un programme est créé par les Terriens, le programme Avatar, qui va leur permettre de contrôler des corps Na’vi clonés associés aux gènes d êtres humains, afin de s’insérer dans la population et de tenter de négocier avec elle. En effet, un clan Na vi important, les Omaticaya, est installé dans un arbre-maison gigantesque situé sur un des principaux gisements de ce minerai convoité par les Terriens, l unobtanium. Les militaires protégeant les équipes de recherche voient d un mauvais œil le projet Avatar, beaucoup trop lent pour eux. Ils sont convaincus que la force brutale tirée de leur avance technologique leur permettrait de conquérir la planète en quelques jours. Le personnage central de l’histoire est Jake Sully, un Marine paraplégique qui, via son avatar, va devoir choisir son camp avec pour enjeu, le destin de la planète.',
 'https://i.pinimg.com/originals/dd/a5/90/dda59083394ab3faa7985032815f26c7.jpg',
 tgenres('Action'));

INSERT INTO LeCatalogue values
('Cendrillon', 'Walt Disney Pictures', (select REF(p) from LesPersonnesA p where p.nom = 'Branagh'),
 DATE '2015-03-15', tacteurs((select REF(p) from LesPersonnesA p where p.nom = 'James'), (select REF(p) from LesPersonnesA p where p.nom = 'Bonham Carter')),
 0, 'Le jour du bal arrive et Cendrillon désespère de ne pas pouvoir y aller. Elle confectionne elle-même sa robe en reprenant celle de sa mère décédée, mais sa méchante belle-mère, Lady Tremaine, la lui déchire juste avant son départ. Effondrée de larmes dans le jardin, Cendrillon croise une vieille femme mendiante et, malgré son chagrin, s emploie à lui rendre quelque menu service. Il s agit en fait de la bonne fée de Cendrillon, qui ne faisait que tester sa bonté. Celle-ci, après quelques brèves explications, lui donne calèche, chevaux, gardes et cocher, et surtout une robe sublime dans laquelle elle pourra se faire passer pour une Princesse. Elle lui précise alors qu il faudra qu elle soit de retour avant minuit où toute la magie s arrêtera. À son arrivée au bal, le Prince se dirige directement vers elle pour la faire danser, tous deux se reconnaissant après leur entrevue dans la forêt. Il compte l épouser mais son père ne veut qu une princesse. Elle s enfuit alors aux coups de minuits laissant le prince sans même lui donner son nom. Il ne trouve qu une pantoufle de verre, perdue durant la fuite de Cendrillon.',
 'https://i.pinimg.com/originals/de/96/95/de96956953ec4611c44c6e71cb61e702.jpg',
 tgenres('Fantastique'));

INSERT INTO LeCatalogue values
('Derrière nos écrans de fumée', 'Exposure Labs', (select REF(p) from LesPersonnesA p where p.nom = 'Orlowski'),
 DATE '2020-09-09', tacteurs(),
 16, 'Derrière nos écrans de fumée (The Social Dilemma) est un documentaire drame américain écrit et réalisé par Jeff Orlowski. Sorti via Netflix le 9 septembre 2020, le film explore la montée en puissance des médias sociaux et les dommages qu ils ont causés à la société, en se concentrant sur son exploitation de ses utilisateurs à des fins financières grâce au capitalisme de surveillance et à l exploration de données, comment sa conception est censée nourrir une dépendance, son utilisation en politique, son impact sur la santé mentale (y compris la santé mentale des adolescents et l augmentation des taux de suicide chez les jeunes utilisateurs de ces réseaux sociaux) et son rôle dans la diffusion des théories du complot et l aide à des groupes tels que les flat-earthers et les suprémacistes blancs.',
 'https://time2watch-fr.fr/images/061ca7956d0d83be5ca3ddec6f984c5d-2020.jpg',
 tgenres('Documentaire'));

INSERT INTO LeCatalogue values
('Inception', 'Warner Bros. Pictures', (select REF(p) from LesPersonnesA p where p.nom = 'Nolan'),
 DATE '2010-01-01', tacteurs((select REF(p) from LesPersonnesA p where p.nom = 'DiCaprio'), (select REF(p) from LesPersonnesA p where p.nom = 'Gordon-Levitt')),
 14, 'Dans un futur proche, les États-Unis ont développé ce qui est appelé le « rêve partagé », une méthode permettant d influencer l inconscient d une victime pendant qu elle rêve, donc à partir de son subconscient. Des « extracteurs » s immiscent alors dans ce rêve, qu ils ont préalablement modelé et qu ils peuvent contrôler, afin d y voler des informations sensibles stockées dans le subconscient de la cible. C est dans cette nouvelle technique que se sont lancés Dominic Cobb et sa femme, Mal. Ensemble, ils ont exploré les possibilités de cette technique et l ont améliorée, leur permettant d emboîter les rêves les uns dans les autres, accentuant la confusion et donc diminuant la méfiance de la victime. Mais l implication du couple dans ce projet a été telle que Mal a un jour perdu le sens de la réalité ; pensant être en train de rêver, elle s est suicidée, croyant ainsi revenir à sa vision de la réalité. Soupçonné de son meurtre, Cobb est contraint de fuir les États-Unis et d abandonner leurs enfants à ses beaux-parents. Il se spécialise dans l « extraction », en particulier dans le domaine de l espionnage industriel ; mercenaire et voleur, il est embauché par des multinationales pour obtenir des informations de leurs concurrents commerciaux.',
 'https://img-4.linternaute.com/hjM5dgLFPajbUWUmTVDwLHOqt3c=/1240x/19547719906f480d80a7c0a77d93f6be/ccmcms-linternaute/127232.jpg',
 tgenres('Thriller'));