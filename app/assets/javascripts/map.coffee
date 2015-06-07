#$ ->
#  $.get "/bars", (data) ->
#    $.each data, (index, item) ->
#      $("#bars").append "<li>Bar " + item.name + "</li>"
#initialize ->
$ ->
  mapCanvas = document.getElementById('map-canvas');
  mapOptions = {
    center: new google.maps.LatLng(44.5403, -78.5463),
    zoom: 8,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  }
  map = new google.maps.Map(mapCanvas, mapOptions)
  

#google.maps.event.addDomListener(window, 'load', initialize);