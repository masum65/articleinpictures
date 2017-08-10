<template>
  <div class="container main-container">
    <navbar />

    <h1>Oops! Looks like the page you're looking for does not exist.</h1>

    <foot />
  </div>
</template>

<script>
import Navbar from '@/components/Navbar'
import Footer from '@/components/Footer'

export default {
  name: '404',
  components: {
    'navbar': Navbar,
    'foot': Footer
  }
}
</script>

<style scoped>
.main-container {
  margin-top: 70px;
  text-align: center;
}
</style>
