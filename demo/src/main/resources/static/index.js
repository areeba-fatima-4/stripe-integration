// The max and min number of photos a customer can purchase
var MIN_PHOTOS = 1;
var MAX_PHOTOS = 10;

var quantityInput = document.getElementById('quantity-input');
quantityInput.addEventListener('change', function (e) {
  // Ensure customers only buy between 1 and 10 photos
  if (quantityInput.value < MIN_PHOTOS) {
    quantityInput.value = MIN_PHOTOS;
  }
  if (quantityInput.value > MAX_PHOTOS) {
    quantityInput.value = MAX_PHOTOS;
  }
});

/* Method for changing the product quantity when a customer clicks the increment / decrement buttons */
var addBtn = document.getElementById("add");
var subtractBtn = document.getElementById("subtract");
var updateQuantity = function (evt) {
  if (evt && evt.type === 'keypress' && evt.keyCode !== 13) {
    return;
  }
  var delta = evt && evt.target.id === 'add' && 1 || -1;

  addBtn.disabled = false;
  subtractBtn.disabled = false;

  // Update number input with new value.
  quantityInput.value = parseInt(quantityInput.value) + delta;

  // Disable the button if the customers hits the max or min
  if (quantityInput.value == MIN_PHOTOS) {
    subtractBtn.disabled = true;
  }
  if (quantityInput.value == MAX_PHOTOS) {
    addBtn.disabled = true;
  }
};

addBtn.addEventListener('click', updateQuantity);
subtractBtn.addEventListener('click', updateQuantity);

document.forms['s1'].addEventListener('submit', (event) => {
     const quaint = document.getElementById('quantity-input');
    event.preventDefault();
    // TODO do something here to show user that form is being submitted
    //event.target.action
    fetch('/create-checkout-session?planId=9e8e17ec-87ff-4dd1-bf58-9b1ec3e3bf78&userId=5fe4d97a-6bbe-48f1-8d4c-303b23de1725&quantity='+quaint.value, {
        method: 'POST',
        body: new URLSearchParams(new FormData(event.target)) // event.target is the form
    }).then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json(); // or response.text() or whatever the server sends
    }).then((body) => {
        window.location.href = body.url;
    }).catch((error) => {
        // TODO handle error
    });
});