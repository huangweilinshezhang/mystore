(function ($) {
    "use strict";    
    
  /*----------------------------------------
  Sticky Header Activation
  ------------------------------------------*/
  $(window).on('scroll', function () {
    if ($(this).scrollTop() > 0) {
      $('.sticky-header').addClass('sticky');
    } else {
      $('.sticky-header').removeClass('sticky');
    }
  });

  /*--
      Custom script to call Background
      Image & Color from html data attribute
  -----------------------------------*/
  $('[data-bg-image]').each(function () {
    var $this = $(this),
        $image = $this.data('bg-image');
    $this.css('background-image', 'url(' + $image + ')');
  });
  $('[data-bg-color]').each(function () {
      var $this = $(this),
          $color = $this.data('bg-color');
      $this.css('background-color', $color);
  });

  /*---------------------------
    Hero Slider Activation
  -----------------------------------*/
  var mySwiper = new Swiper('.slider-container.swiper-container', {
    spaceBetween: 30,
    centeredSlides: true,
    speed: 3000,
    autoplay: false,
    loop: true,
    effect: 'fade',
    fadeEffect: {
      crossFade: true
    }
  });
  /*---------------------------
    Carousel Activation
  -----------------------------------*/
    var swiper = new Swiper('.testimonial-carousel', {
      spaceBetween: 30,    
      slidesPerView: 1,
      centeredSlides: true,
      roundLengths: true,
      loop: true,
      loopAdditionalSlides: 30,
      pagination: {
        el: ".swiper-pagination",
        clickable: true,
      },
    });
	/*---------------------------
    Magnific Popup
  -----------------------------------*/
  $('.video-popup').magnificPopup({
    type: 'iframe'
	});

	/*-----------------------------------------
    Off Canvas Mobile Menu
  -------------------------------------------*/
  $(".header-action-btn-menu").on('click', function () {
    $(".header-action-btn-menu").addClass('open');
    $(".mobile-menu-wrapper").addClass('open');
	});

	$(".offcanvas-btn-close,.offcanvas-overlay").on('click', function () {
		$(".header-action-btn-menu").removeClass('open');
		$(".mobile-menu-wrapper").removeClass('open');
	});

  /*-----------------------------------------
		Off Canvas Search
	-------------------------------------------*/

	$(".header-action-search").on('click', function () {
		$("body").addClass('fix');
		$(".offcanvas-search").addClass('open');
	});

	$(".offcanvas-btn-close,.body-overlay").on('click', function () {
		$("body").removeClass('fix');
		$(".offcanvas-search").removeClass('open');
	});
	
	/*----------------------------------------
    Responsive Mobile Menu
  ------------------------------------------*/
  //Variables
  var $offCanvasNav = $('.mobile-menu, .category-menu'),
  $offCanvasNavSubMenu = $offCanvasNav.find('.dropdown');

  //Close Off Canvas Sub Menu
  $offCanvasNavSubMenu.slideUp();

  //Category Sub Menu Toggle
  $offCanvasNav.on('click', 'li a, li .menu-expand', function(e) {
  var $this = $(this);
    if ( ($this.parent().attr('class').match(/\b(menu-item-has-children|has-children|has-sub-menu)\b/)) && ($this.attr('href') === '#' || $this.hasClass('menu-expand')) ) {
      e.preventDefault();
      if ($this.siblings('ul:visible').length){
        $this.parent('li').removeClass('active');
        $this.siblings('ul').slideUp();
      } else {
        $this.parent('li').addClass('active');
        $this.closest('li').siblings('li').removeClass('active').find('li').removeClass('active');
        $this.closest('li').siblings('li').find('ul:visible').slideUp();
        $this.siblings('ul').slideDown();
      }
    }
  });

  /*----------------------------------------*/
  /*  When document is loading, do
  /*----------------------------------------*/
  var varWindow = $(window);
  varWindow.on('load', function() {
    AOS.init({
      once: true,
    });
  });

  /*----------------------------------------*/
  /*  Project Filter
  /*----------------------------------------*/
  $('.gallery-filter-nav').on( 'click', 'button', function() {
    var filterValue = $(this).attr('data-filter');
    $grid.isotope({ filter: filterValue });
    
    $(this).siblings('.active').removeClass('active');
    $(this).addClass('active');
  });

  var $grid = $('.gallery-filter-wrapper').isotope({
      itemSelector: '.filter-item',
      percentPosition: true,
  });

  /*--
      Client's Activation
  -----------------------------------*/    
  var brands = new Swiper('.brand-slider.swiper-container', {
    slidesPerView: 1,
    // init: false,
    loop: true,
    pagination: false,
    breakpoints: {
      0: {
        slidesPerView: 2,
        spaceBetween: 80,
      },
      480: {
        slidesPerView: 3,
        spaceBetween: 140,
      },
      768: {
        slidesPerView: 4,
        spaceBetween: 140,
      },
      992: {
        slidesPerView: 4,
        spaceBetween: 200,
      },
      1200: {
        slidesPerView: 5,
        spaceBetween: 140,
      },
    }
  });
  
  /*---------------------------------
  MailChimp
  -----------------------------------*/
  $('#mc-form').ajaxChimp({
    language: 'en',
    callback: mailChimpResponse,
    // ADD YOUR MAILCHIMP URL BELOW HERE!
    url: 'http://devitems.us11.list-manage.com/subscribe/post?u=6bbb9b6f5827bd842d9640c82&amp;id=05d85f18ef'
  });
  function mailChimpResponse(resp) {
    if (resp.result === 'success') {
        $('.mailchimp-success').html('' + resp.msg).fadeIn(900);
        $('.mailchimp-error').fadeOut(400);
    } else if (resp.result === 'error') {
        $('.mailchimp-error').html('' + resp.msg).fadeIn(900);
    }
  }
	/*-------------------------
     Ajax Contact Form 
  ---------------------------*/
  $(function() {

    // Get the form.
    var form = $('#contact-form');

    // Get the messages div.
    var formMessages = $('.form-messege');

    // Set up an event listener for the contact form.
    $(form).on('submit', function(e) {
        // Stop the browser from submitting the form.
        e.preventDefault();

        // Serialize the form data.
        var formData = $(form).serialize();

        // Submit the form using AJAX.
        $.ajax({
          type: 'POST',
          url: $(form).attr('action'),
          data: formData
        })
        .done(function(response) {
          // Make sure that the formMessages div has the 'success' class.
          $(formMessages).removeClass('error');
          $(formMessages).addClass('success');

          // Set the message text.
          $(formMessages).text(response);

          // Clear the form.
          $('#contact-form input,#contact-form textarea').val('');
        })
        .fail(function(data) {
          // Make sure that the formMessages div has the 'error' class.
          $(formMessages).removeClass('success');
          $(formMessages).addClass('error');

          // Set the message text.
          if (data.responseText !== '') {
              $(formMessages).text(data.responseText);
          } else {
              $(formMessages).text('Oops! An error occured and your message could not be sent.');
          }
        });
    });

  });
  /*----------------------------------------*/
  /*  Scroll to top
  /*----------------------------------------*/
  function scrollToTop() {
    var $scrollUp = $('#scroll-top'),
      $lastScrollTop = 0,
      $window = $(window);
    $window.on('scroll', function () {
      var st = $(this).scrollTop();
      if (st > $lastScrollTop) {
          $scrollUp.removeClass('show');
      } else {
        if ($window.scrollTop() > 200) {
            $scrollUp.addClass('show');
        } else {
            $scrollUp.removeClass('show');
        }
      }
      $lastScrollTop = st;
    });

    $scrollUp.on('click', function (evt) {
      $('html, body').animate({scrollTop: 0}, 600);
      evt.preventDefault();
    });
  }
  scrollToTop();

})(jQuery);