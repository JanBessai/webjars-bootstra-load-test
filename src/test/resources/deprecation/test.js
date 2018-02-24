requirejs.onError = function(err) {
    $('#errors').text(err);
};
require(['bootstrap'],
  function(bootstrap) {
      $(function () { $('#status').text("Bootstrap OK!"); });
  },
  function (err) {
      $(function () { $('#errors').text(err); });
  });
