<template>
  <div>
    <navbar />

    <div id="main-input-box">
      <div class="container">
        <h1>Analyze Your Article</h1>
        <p>
          Simply enter the URL to your article in the input below, or copy &amp; paste the entire article, and click Analyze.
          We will analyze your article and return pictures based on the content.
        </p>
        <!--<form class="form-inline">
          <div class="form-group">
            <input v-model="articleText" type="text" class="form-control input-lg" id="exampleInputAmount" placeholder="Enter URL or paste article">
          </div>

          <button v-on:click="analyze" class="btn btn-primary btn-lg">Analyze</button>
        </form>-->
        <div class="col-xs-12">
          <input v-model="articleText" class="form-control input-lg" type="text" placeholder="Enter URL or paste article" />
        </div>
        <div class="col-xs-12">
          <button v-on:click="analyze" class="btn btn-primary input-lg">Analyze</button>
        </div>
      </div><!-- /.container -->
    </div>

    <div class="container">
      <div class="row">
        <div class="col-sm-4 col-xs-12">
          <h2><i class="glyphicon glyphicon-pencil" aria-hidden="true"></i> Step 1</h2>
          <p>Enter the URL to your article in the input above, or simply copy &amp; paste it, and click analyze.</p>
        </div><!-- /.col-sm-4 -->
        <div class="col-sm-4 col-xs-12">
          <h2><i class="glyphicon glyphicon-time" aria-hidden="true"></i> Step 2</h2>
          <p>Give us a few moments to analyze your article and find relevant pictures.</p>
        </div><!-- /.col-sm-4 -->
        <div class="col-sm-4 col-xs-12">
          <h2><i class="glyphicon glyphicon-download" aria-hidden="true"></i> Step 3</h2>
          <p>Enjoy a list of royalty free stock images you're free to use in your article.</p>
        </div><!-- /.col-sm-4 -->
      </div><!-- /.row -->
    </div>

    <foot />
  </div>
</template>

<script>
import Navbar from '@/components/Navbar'
import Footer from '@/components/Footer'

export default {
  name: 'front',
  components: {
    'navbar': Navbar,
    'foot': Footer
  },
  data () {
    return {
      loading: false,
      articleText: ''
    }
  },
  methods: {
    analyze () {
      this.$router.push({ name: 'analyze', params: { articleText: this.articleText } })
    }
  }
}
</script>

<style scoped>
#main-input-box {
  width: auto;
  height: 419px;
  margin-top: 50px;
  text-align: center;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  padding: 0px;
  color: #f8f8f8;
  border-bottom: 1px solid #000;
  background: url('../assets/main-search-box-bg.jpg');
}
#main-input-box p {
  font-size: 1.3em;
}
.col-xs-12 button {
  margin-top: 10px;
}
</style>
