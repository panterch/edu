package ch.panter.decorator.reader;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;


class DowncaseReader extends FilterReader
{

  public DowncaseReader( Reader in )
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
      
      for ( int i = off; i < off + numchars; i++ )
      {
        cbuf[i] = Character.toLowerCase(cbuf[i]);
      }
    }
    return numchars;
  }
}