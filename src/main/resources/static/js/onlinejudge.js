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

var send = getUrlParameter('send');

var myFunction = (function(i) {

    $.ajax({
      url: "http://localhost:8080/api/compile/" + send + "/" + i,
      context: document.body
    }).done(function(data) {
      console.log(data);
      if( data ){
        console.log("Uruchamiam " + i+1)
        myFunction(i+1);
      }
    });

} );


$(function(){
   // myFunction(1);
});
