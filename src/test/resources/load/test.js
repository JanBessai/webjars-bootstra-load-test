requirejs.onError = function(err) {
    var oldErrors = $('#errors').text();
    $('#errors').text(oldErrors + err + "; ");
};
require(['bootstrap'],
  function(bootstrap) {
      $(function() { $('#status').text("Bootstrap OK!"); });
  },
  function (err) {
      $(function () {
          var oldErrors = $('#errors').text();
          $('#errors').text(oldErrors + err + "; ");
      });
  });
