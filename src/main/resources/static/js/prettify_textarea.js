$(function() {
  
  /* SWAP TEXTAREA & PRETTY PRINT *
   * ============================ */
	$('[data-code]').on('blur', function() {
    if (this.value.length <= 0) { // Don't style empty textareas
      return false;
    }
    
    var $this = $(this).css('display', 'none'), // Hide textarea
    
        $target = $($this.data('code')) // Select prettify target
                  .removeClass('prettyprinted') // Remove class to allow for restyling
                  .html( // Use HTML special characters
                    this.value
                      .replace(/&/g, "&amp;")
                      .replace(/</g, "&lt;")
                      .replace(/>/g, "&gt;")
                      .replace(/"/g, "&quot;")
                      .replace(/'/g, "&#039;")
                  );
    
    $target.css('display', 'block'); // Show code after styling
  })
  .next('.prettyprint').on('click', function() {
    // Toggle visibility of prettified code when editing
  	$(this).css('display', 'none')
    .prev('[data-code]').css('display', 'block');
  }).trigger('click'); // Set visibility on load

});