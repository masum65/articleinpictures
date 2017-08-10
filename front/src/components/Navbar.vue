<template>
  <nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <router-link :to="{ path: '/' }">
          <a class="navbar-brand">Article In Pictures</a>
        </router-link>
      </div>
      <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
          <router-link :to="{ path: '/analyze' }" tag="li" active-class="active"><a>Analyze</a></router-link>
        </ul>
      </div><!--/.nav-collapse -->
    </div>
  </nav>
</template>
