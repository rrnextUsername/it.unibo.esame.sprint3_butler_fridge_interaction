comando( addFood, NomeCibo ) :-
	%%prerequisiti(),
	assert( azione( querySync, NomeCibo, null, null ) ).
	%%assert( azione( check, presente, null, null ) ), %% Forse non serve, l’actor non torna a TODO
	%%assert( ciboRichiesto( NomeCibo ) ),
	%%assert( azione( ciboPresente, null, null, null ) ). %% Forse non serve, come sopra

comando( successAddFood, NomeCibo) :-
	%%assert( azione( movimento, frigo, null, null ) ),
	%%assert( azione( spostaEsterno, frigo, robot, ( NomeCibo, cibo ) ) ),
	%%assert( azione( movimento, tavolo, null, null ) ),
	%%assert( azione( spostaInterno, robot, tavolo, ( NomeCibo, cibo ) ) ),
	%%assert( azione( movimento, home, null, null ) ).
	assert( azione( check, 1, 2, 3 ) ).
	
comando( testASync, null ):-
	assert( azione( queryAsync, null, null, null ) ).
	

	