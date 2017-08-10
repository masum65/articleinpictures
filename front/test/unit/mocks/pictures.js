export default {
  one: {
    status: 200,
    data: {
      hits: 1,
      pictures: [ { id: 1, thumbnailUrl: 'http://www.image.com/1' } ]
    }
  },
  multiple: (n) => {
    const ids = []
    for (let i = 0; i < n; i++) ids.push(i)

    return {
      status: 200,
      data: {
        hits: n,
        pictures: ids.map((i) => {
          return {
            id: i,
            thumbnailUrl: `http://www.image.com/${i}`
          }
        })
      }
    }
  },
  zero: {
    status: 200,
    data: {
      hits: 0,
      pictures: []
    }
  }
}
