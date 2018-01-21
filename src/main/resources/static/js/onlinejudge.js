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
$( "#loader" ).html("<i class=\"fa fa-cog fa-spin fa-4x fa-fw\"></i> <span class=\"sr-only\">Loading...</span> <p class=\"font-weight-bold\"> Kompiluję...</p>");
$( "#last_solution" ).addClass("hidden");
    if( s ) {
        $.ajax({
          url: "http://localhost:8080/api/compile/" + s,
          context: document.body
        }).done(function(data) {
          if( data ){
            $( "#last_solution" ).removeClass("hidden");
            $( "#loader" ).addClass("hidden");
            location.reload();
          }
        })
        .fail(function() {
            $( ".alert").removeClass("alert-info");
            $( ".alert").addClass("alert-danger");
            $( ".alert").text("Utracono połączenie z serwerem kompilatora. Spróbuj ponownie za jakiś czas.");

            $( "#loader" ).html("<i class=\"fa fa-exclamation-circle fa-4x text-danger\" aria-hidden=\"true\"></i> <p class=\"font-weight-bold text-danger\"> Kompilator umarł</p>");
          });
    }
});
