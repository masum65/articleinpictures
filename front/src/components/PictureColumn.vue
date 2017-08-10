<template>
  <div class="col-sm-4 col-xs-12 col-picture-outer" :class="cls">
    <div v-for="picture in pictures" class="col-xs-12 col-picture-inner">
      <div class="square-well">
        <a :href="picture.url" target="_blank">
          <img class="img-responsive center-block" :src="picture.thumbnailUrl" />
        </a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'picture-column',
  props: [ 'pictures', 'cls' ]
}
</script>

<style scoped>
.col-picture-outer {
  padding: 0;
}
.col-picture-inner {
  padding: 0 5px 0 5px !important;
}
.square-well {
  margin-bottom: 10px;
}
</style>
