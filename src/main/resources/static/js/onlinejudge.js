var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

var s = getUrlParameter('s');

$(function(){
$( "#loader" ).removeClass("hidden");
$( "#last_solution" ).text("Kompiluję...");
    if( s ) {
        $.ajax({
          url: "http://localhost:8080/api/compile/" + s,
          context: document.body
        }).done(function(data) {
          if( data ){
            $( "#last_solution" ).text("Gotowe, odśwież stronę");
            $( "#loader" ).addClass("hidden");
            location.reload();
          }
        });
    }
});
