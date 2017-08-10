import axios from 'axios'

const expression = /[-a-zA-Z0-9@:%_+.~#?&//=]{2,256}\.[a-z]{2,4}\b(\/[-a-zA-Z0-9@:%_+.~#?&//=]*)?/gi
const regex = new RegExp(expression)

const options = {
  method: 'post',
  headers: {
    'Content-Type': 'text/plain'
  }
}

export default {
  analyze (articleText) {
    const pieces = articleText.split(' ')

    if (pieces.length === 1 && pieces[0].match(regex)) {
      return axios(Object.assign(options, {
        url: `${process.env.API_URL}/pictures/url`,
        data: pieces[0]
      }))
    } else {
      return axios(Object.assign(options, {
        url: `${process.env.API_URL}/pictures/text`,
        data: articleText
      }))
    }
  }
}
