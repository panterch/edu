package ch.panter.decorator.reader;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;


class HTMLReader extends FilterReader
{
  private boolean intag = false;

  public HTMLReader( Reader in )
  {
    super( in );
  }

  @Override
  public int read() throws IOException
  {
    char[] buf = new char[1];
    return read( buf, 0, 1 ) == -1 ? -1 : buf[0];
  }

  @Override
  public int read( char[] cbuf, int off, int len ) throws IOException
  {
    int numchars = 0;

    while ( numchars == 0 )
    {
      numchars = in.read( cbuf, off, len );

      if ( numchars == -1 ) // EOF?
        return -1;

      int last = off;

      for ( int i = off; i < off + numchars; i++ )
      {
        if ( ! intag )
        {
          if ( cbuf[i] == '<' )
            intag = true;
          else
            cbuf[last++] = cbuf[i];
        }
        else if ( cbuf[i] == '>' )
          intag = false;
      }
      numchars = last - off;
    }
    return numchars;
  }
}