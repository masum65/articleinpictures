<template>
  <div>
    <navbar />

    <div class="container main-container">
      <div class="row">
        <div class="analyze-box" v-bind:class="{ 'centerize': pristine }">
          <div class="container">
            <div class="col-sm-12 col-xs-12">
              <input v-model="articleText" class="form-control input-lg" type="text" placeholder="Enter URL or paste article" />
            </div>
            <div class="col-xs-12">
              <button id="analyze-button" v-on:click="submit" class="btn btn-primary input-lg">Analyze</button>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <hr v-if="results.length > 0" />
        <div class="col-lg-12">
          <p class="info-fixed" v-if="!pristine && !loading && results.length == 0 && !error">
            Unfortunately we could not find any pictures for this article :(
          </p>
          <p class="info-fixed" v-if="error">
          Oops, looks like we&#39;re experiencing some problems. Please try again later.
          </p>
          <p class="info-fixed" v-if="loading">
            <i class="fa fa-spinner fa-pulse fa-3x fa-fw"></i>
            <span class="sr-only">Loading...</span>
          </p>
        </div>

        <picture-column cls="pictures-left" :pictures="resultsLeft" />
        <picture-column cls="pictures-middle ":pictures="resultsMiddle" />
        <picture-column cls="pictures-right" :pictures="resultsRight" />

      </div>
    </div>

    <foot />
  </div>
</template>

<script>
import Navbar from '@/components/Navbar'
import Footer from '@/components/Footer'
import PictureColumn from '@/components/PictureColumn'
import ArticleService from '@/services/ArticleService'

export default {
  name: 'analyze',
  components: {
    'navbar': Navbar,
    'foot': Footer,
    'picture-column': PictureColumn
  },
  data () {
    return {
      loading: false,
      pristine: true,
      error: null,
      articleText: '',
      results: [],
      resultsLeft: [],
      resultsMiddle: [],
      resultsRight: []
    }
  },
  created () {
    if (this.$route.params.hasOwnProperty('articleText')) {
      this.articleText = this.$route.params.articleText
      this.analyze(this.$route.params.articleText)
    }
  },
  methods: {
    submit (evt) {
      evt.preventDefault()

      this.analyze(this.articleText)
    },
    analyze (text) {
      if (text) {
        this.results = []
        this.resultsLeft = []
        this.resultsMiddle = []
        this.resultsRight = []
        this.loading = true
        this.pristine = false
        this.error = null

        ArticleService.analyze(text)
          .then((results) => {
            this.results = results.data.pictures
            for (let i = 0; i < this.results.length; i++) {
              if (i % 3 === 0) this.resultsLeft.push(this.results[i])
              else if (i % 3 === 1) this.resultsMiddle.push(this.results[i])
              else this.resultsRight.push(this.results[i])
            }
          })
          .catch((error) => {
            this.error = error
          })
          .then(() => {
            this.loading = false
          })
      }
    }
  }
}
</script>

<style scoped>
.main-container {
  margin-top: 70px;
  text-align: center;
}
.info-fixed {
  position: fixed;
  left: 0;
  right: 0;
  top: 50%;
}
.centerize {
  position: absolute;
  bottom: 50%;
  height: 30px;
}
.analyze-box button {
  margin-top: 10px;
}
</style>
