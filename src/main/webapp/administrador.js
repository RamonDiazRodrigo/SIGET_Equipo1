$('#visibilityHidden').click(function(e) {
  if( $('#hide-me').css('visibility') != 'hidden' ) {
    $('#hide-me').css('visibility', 'hidden');
    
  } else {
    $('#hide-me').css('visibility', 'visible');
  }
  $('#visibilityHidden').css('visibility', 'hidden');
  
});

$('#volver').click(function(e) {
	 $('#hide-me').css('visibility', 'hidden');
	 $('#visibilityHidden').css('visibility', 'visible');
});

